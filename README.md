[![CI pipeline with Maven](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/ci.yml/badge.svg)](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/ci.yml)
[![Docker build and push to ECR](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/docker.yml/badge.svg)](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/docker.yml)

Final PGR301 Exam - Kandidatnummer: 1009

### Del 1 - DevOps prinsipper
•Det er flere utfordringer med dagens utviklingsprosess. Fra før i dag var man nødt til å manuelt gå inn på GitHub Actions for å kunne kjøre continuous integration(fra nå av CI) workflowen. Docker workflowen er ikke konguerert riktig og kjøres ikke automatisk. Dette er sikk i strid med devops prinsippet om automatikk. Grunnmuren til devops er å kunne kontinuerlig levere og integrere kode. En del av å kontinuerlig levere er at gjentagende arbeidsoppgaver automatiseres, og å automatisere verifiseringen av test-vellykkethet og kompilering er et minimumskrav. En del av å kunne kontinuerlig integrere er at test-vellykketheten er representativ for funksjonaliteten til løsningen. Så langt har applikasjonen kun en test, så om de så hadde fått CI til å kjøre automatisk ville ikke det være et representativt bilde på hvorvidt applikasjonen fortsatt kjører. Itilegg brytes både kontinuerlig levering og integrerings prinsippet der.

•Devops går ut på å korte ned systemets utviklingsprosess og muligheten til å kontinuerlig utvikle kode til produksjon. DevOps fasiliterer for å utvikle og teste mindre seksjoner med kode, som problemfritt kan integreres i eksisterende kodebase. For at disse prinsippene skal realiseres i shopifly er man først og fremst nødt til å presse frem en større test-dekkning. Produsere tester som verifiserer at funksjonaliteten til løsningen er operasjonell. Fra der ville man satt opp en fungerende og automatisk continuous integration workflow som et statussymbol på det. For å ta det videre derifra ville man satt opp en docker workflow som pushet et container image til eksempelhvis et ECR repository. For at koden skal komme ditt, er CI nødt til verifisere gitte kodebit, og så skal docker worflowen produsere et container image basert på det, og pushe det til et remote ECR-repository.

•Dersom de hadde gjort det kunne de økt frekvensen av leveranser opp til hver dag, minimert ustabilitet i systemet, redusert ledetiden til ny funksjonalitet, skrelt av et par QA-og-test-ansatte og fått automatiske tilbakemeldinger på applikasjonens tilstand.   
•Ved å senke leveranse hyppigheten og øke kontroll og QA bryter man fort med flere devops prinsipper. Det bryter med ende til ende prinsippet, hvor utviklerene ikke vil være involvert gjennom kodens livssyklus. Det bryter med prinsippet om delt ansvar, da kontroll og QA vil ta ansvar for kvaliteten til koden du har produsert. Det bryter med automasjonsprinsippet, da det vil være mange øyne og mange stopp for en kodebit fra utvikling til produksjon. Det bryter med kontinuerlig forbedring, fordi du som utvikler vil ha langt vanskeligere for å forbedre deg da det ikke er deg som forbedrer din kode.    

•En bedre tilnærming fra et devops perspektiv ville vært å implementere muligheten til å måle hvorvidt det er fremskritt i riktig rettning for applikasjonen. Det gjøres ved å implementere metrics i eksempelvis aws cloudwatch.
Der får man automatiske tilbakemeldingen ved å måle viktige metrics ved applikasjonen som gir verdifull innsikt i tilstanden til komponentene som blir pushet til produksjon, 
og applikasjonens helhetlig tilstand. Målingen av en applikasjons metrics vil gi et klart bilde på tilstanden og "helsa" til applikasjonen. Ettersom man ofte tar stilling 
til mindre blokker med kode vil fallgruver og bugs være lettere å identifisere, raskere å fikse, og resultere i kortere nedetid.

•Ved at teamet leverer fra seg kode til en annen avdeling for drift, resulterer det i en holdningskultur hvor det ofte er greit bare er å bli "ferdig" og få noe til å fungere minimalt. Fordi utviklerene ikke kommer til å se koden igjen.  Dersom Shopifly hadde fulgt devops prinsippet som handler om delt ansvar, ville det vært annerledes. Det burde være et delt ansvar mellom utviklerene og drifterne av en IT-løsning. Dette hadde ført til at utviklere vil ha et delt ansvar for drifting og vedlikehold av hele løsningen, itillegg til utviklingen. Dette gir utviklerne et større overblikk over hvilket produksjonskrav ny kode har. Det vil også gi utviklerene innsyn i hvilket funksjonelle krav løsningen behøver fra et operasjonelt perspektiv.         

•Devops fokuserer på at alle på teamet skal ha en bedrifts-orientert tankemåte, ved å fjerne unødvendig kommunikasjonslag mellom utviklere, driftere og interessenter, gir det utviklerene større motivasjon til å ta eierskap for prosjektet og bli drevet fremover av bedriftens suksess. Samtidig, ved å jobbe i et devops orientert team, setter man pris på muligheten til å dele verdifull informasjon med andre. Det å være flink til å dele tilbakemedlinger, beste måter å gjøre ting på og kunnskap, innad i et team, skaper en form for kollektiv kunnskaps-database og fjerner sosiale begrensninger. Dette gjør hele teamet mer skikket for vanskelige prosesser, motgang og problemer. En fri informasjonsflyt og insentiveringen av det, blir lettere dersom man jobber på samme avdeling, og dersom alle jobber med "alt". Disse egenskapene ved et team vil føre til at det automatisk, kontinuerlig forbedrer seg selv.    



### Del 2 - Continuous Integration
#### oppgave 3.
Det er dumt ifølge devops prinsipper å pushe kode direkte på main/master-branch. Derfor ønsker man å sette opp en 'Branch protection rule' som det heter. \
Desverre så sier github "Your rules won't be enforced on this private repository until move to a GitHub Team or Enterprise organization account". Uansett så skal jeg forklare deg hvordan man setter opp en slik regel.
1. Først gå på prosjektet på GitHub og inn i 'Settings'. 
2. Gå så på menyvalget 'Branches'. 
3. Der trykker man på 'Add branch protection rule'.
4. Da har man kommet seg hit. Under 'Branch name pattern' skal du legge inn main/master alt ettersom hva som er din hoved-branch.

![img.png](img.png)


5. Ved å huke av 'Require a pull request before merging' utelukker du muligheten til å pushe kode direkte på branchen skrevet inn ovenfor. I dette tilfellet master-branchen.
6. Ved å huke av 'Require approvals' og velge 'Require number of approvals before merging: 1' krever du at for å få kode til master-branch, er man nødt til å åpne en pull-request fra en annen ubeskyttet branch. Så få godkjent pull-requesten av 1 person før branchen kan bli merget med master-branch.
7. Scroll litt lenger ned og huk av 'Require status checks to pass before merging' og 'Require branches to be up to date before merging' og skriv inn 'build' og 'build_docker_image' i feltet.


![img_1.png](img_1.png)

8. Ved å gjøre dette krever man at for pull-requesten skal kunne merges i master-branch må statusene på begge jobbene nevnt ovenfor være er vellykkede, altså være verifisert av GitHub Actions.

### Del 3 - Docker
#### oppgave 1.
For å få worklfowen til å fungere med din DockerHub konto må du gjøre følgende:
1. For å få worklflowen til å fungere med din DockerHub konto må du først og fremst ha en DockerHub bruker. Da kan det være lurt å lagre DockerHub
   brukernavn og passord som en repository secret på GitHub. Under 'Settings', så 'Secrets' så 'Action', og legge til 2 'New repository secret'.
    1. DOCKER_HUB_USERNAME, med brukernavnet ditt.
    2. DOCKER_HUB_PASSWORD, med passordet ditt.  
       Så du vil trenge en login-action med de.


2. Så vil du vil trenge en metadata-action med en image-tag, som beskriver hvor containeren skal lagres remote, og tagen||navnet til containeren du skal pushe.\
   Som ville vært f.eks:   
   images: mitt-dockerhub-brukernavn/tag||navn-på-container.


3. Så vil man behøve en build-push-action med push-tag satt til true, slik at imaget vil bli pushet til dockerhub hvis bygget er suksessfullt.
   Man vil også trenge en tag opsjon, i formatet mitt-dockerhub-brukernavn/tag||navn-på-container-remote:versjon-av-image


Det er flere ting som er galt med denne workflowen.
Det som utgangspunktlig ble gjort på linje 22, hvor Jim bare skipper testene gjør at imaget ikke vil kompilere. Samtidig som han har satt push: true, under build-push-action. Noe som gjør at imaget ikke vil bli pushet hvis det er bygge-feil.
Jim har også konfigurert workflowen til å liksom finne ut hvor det remote repositoriet er via tags i build-push-action. Noe som ikke vil fungere. Du er nødt til å definere namespacet til hvor Docker skal pushe imaget ditt remote, for så å definere versjon i build-push-action senere. 
Det må gjøres som steg 2. ovenfor. Via en image tag, i metadata-action.
Det å definere hvilken versjon av JDK som skal brukes i workflowen er helt unødvendig. Det skal defineres i Dockerfile, så skal prosjektet så pakkes og bygges der, som workflowene så skal få bygge og runne på.

#### oppgave 2.
Dette er gjort i prosjektet, god lesing:P

#### oppgave 3.
For at sensor skal kunne laste opp container image til sitt eget ECR-repository må man skrifte verdi på variablen 'ecrrepositoryname'(linje 22 ish) i docker.yml til navnet til sensor sitt ECR-repo.
I tillegg må sensor autentisere seg ovenfor AWS, og er nødt til å legge til sin egen AWS access-og secret access Key på GitHub.Igjen, det kan man ved å gå på prosjektet på github, under 'Settings', så 'Secrets', så 'Actions' og 
legge til ny 'New repository secret' med verdiene sensor får fra AWS sitt IAM-system. Så skal det fungere for sensor:)

Det er et god devops prinsipp å ikke commite direkte på master-branch, derfor konfiguerer jeg jobben 'build_docker_image' til å kun kjøres ved pull requester til master branch. Dette fungerer godt i dette prosjektet
da jeg slutter med å pushe direkte på master, og heller lager en feature-branch som så skal pull-requestes inn i master når en feature er ferdig.
Feature-branchen vil fortsatt kjøre jobben 'build' som sjekker om tester kjøres og programmet kompileres hver gang det pushes til feature-branch, men man vil spare run-time minutter i AWS. Da feature-branch slipper
å kjøre 'build_docker_image' på hver eneste push, men kun når det pulles på master-branch.



### Del 5.
#### oppgave 1.
Terraform prøver å opprette en bucket som allerede eksiterer fordi bøttenavnet er hardkodet til varablen "analytics" + candidate_id, som alltid vil være det samme. Det blir aldri lagt til noe unikt hver gang terraform Apply'er, og S3 buckets må være unike.    
Så hver gang workflowen kjører, så lages den samme bucketen. I dette tilfellet er terraform konfiguert til å lage en ny bucket hver gang workflowen blir kjørt, en mer devops
orientert løsning på å lage ressurser hver gang, ville vært å lage et nytt object som blir puttet i den allerede eksisterende bucketen.

Jeg har gjort et svært ærlig forsøk på å prøve å få terreform til å kunne kjøres flere ganger uten å opprette ressurser hver gang.   
Ta en titt på cloudwatch_dashboard, under Import for hva jeg har tenkt og forsøkt på.

#### oppgave 2.   
Ved å legge til denne linja under Terraform Apply: 
if: github.ref == 'refs/heads/master' && github.event_name == 'push'
   - Kjører vi apply kun når noen gjør et push mot main branch.

Ved å legge til denne linja under Terraform Plan:
   if: github.event_name == 'pull_request'
   - Kjører vi plan kun når det lages et pull request.

#### oppgave 3. && Alarmer
Jeg har desverre vært syk under siste del av året, så jeg har ikke hatt anledning til å være å være i forelesning, ei heller hatt    
muligheten til å gi alt og gå all inn på zoom. Jeg har desverre ikke så mye å bidra med på Cloudwatch-delen av eksamen. Har
fått til å lage et dashboard med hjelp av dashboard.tf vell og merke. 

