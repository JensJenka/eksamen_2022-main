package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;


@Component
class NaiveCartImpl implements CartService, ApplicationListener<ApplicationReadyEvent> {

    private final Map<String, Cart> shoppingCarts = new HashMap<>();

    private final LongAdder checkedoutCounter = new LongAdder();
    private MeterRegistry meterRegistry;

    public NaiveCartImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Cart getCart(String id) {
        return shoppingCarts.get(id);
    }

    @Override
    public Cart update(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(UUID.randomUUID().toString());
        }
        shoppingCarts.put(cart.getId(), cart);
        return shoppingCarts.put(cart.getId(), cart);
    }

    @Override
    @Timed(value = "checkout_latency")
    public String checkout(Cart cart) {
        shoppingCarts.remove(cart.getId());
        checkedoutCounter.increment();
        return UUID.randomUUID().toString();
    }

    @Override
    public List<String> getAllsCarts() {
        return new ArrayList<>(shoppingCarts.keySet());
    }

    // @author Jim; I'm so proud of this one, took me one week to figure out !!!
    public float total() {
        return shoppingCarts.values().stream()
                .flatMap(c -> c.getItems().stream()
                        .map(i -> i.getUnitPrice() * i.getQty()))
                .reduce(0f, Float::sum);
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Gauge.builder("carts_count", shoppingCarts,
                b -> b.values().size()).register(meterRegistry);

        Gauge.builder("carts_value", shoppingCarts, b -> {
            float totalValue = b.values().stream()
                    .flatMap(c -> c.getItems().stream()
                            .map(i -> i.getUnitPrice() * i.getQty()))
                    .reduce(0f, Float::sum);

            return totalValue;
        }).register(meterRegistry);

        Gauge.builder("checkout_count", checkedoutCounter,
                b -> b.longValue()).register(meterRegistry);

    }
}
