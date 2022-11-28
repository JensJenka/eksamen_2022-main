[![CI pipeline with Maven](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/ci.yml/badge.svg)](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/ci.yml)
[![Docker build and push to ECR](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/docker.yml/badge.svg)](https://github.com/JensJenka/eksamen_2022-main/actions/workflows/docker.yml)

Final PGR301 Exam - Kandidatnummer: 1009

Del 1 - DevOps prinsipper






### Del 2 - Continuous Integration
#### oppgave 3.
1. Gå på prosjektet på GitHub og inn i 'Settings'. 
2. Gå så på menyvalget 'Branches'. 
3. Der trykker man på 'Add branch protection rule'.
4. Da har man kommet seg hit. Under 'Branch name pattern' skal du legge inn main/master alt ettersom hva som er din hoved-branch.
![img.png](img.png)
5. Ved å huke av 'Require a pull request before merging' utelukker du muligheten til å pushe kode direkte på branchen skrevet inn ovenfor. I dette tilfellet master-branchen.
6. Ved å huke av 'Require approvals' og velge 'Require number of approvals before merging: 1' krever du at for å pushe kode til master-branch, er man nødt til å åpne en pull-request fra en annen branch. Så få godkjent pull-requesten av 1 person før branchen kan bli merget med master-branch.
7. Scroll litt lenger ned og huk av 'Require status checks to pass before merging' og 'Require branches to be up to date before merging' og skriv inn 'build' og 'build_docker_image' i feltet.
![img_1.png](img_1.png)
8. Ved å gjøre dette krever man at for at pull-requesten skal kunne merges i master-branch er at statusene på jobbene du skriver inn i felet er vellykkede.

### Del 3 - Docker
#### oppgave 1.
#### oppgave 2.
#### oppgave 3.
For at sensor skal kunne laste opp container image til sitt eget ECR-repository må man skrifte verdi på variablen 'ecrrepositoryname'(linje 22 ish) i docker.yml til navnet til sensor sitt ECR-repo.
I tillegg må sensor autentisere seg ovenfor AWS, og er nødt til å legge til sin egen AWS access-og secret access Key på GitHub. Det kan man ved å gå på prosjektet på github, under 'Settings', så 'Secrets', så 'Actions' og 
legge til ny 'New repository secret' med verdiene sensor får fra AWS sitt IAM-system. Så skal det fungere for sensor:)

Det er et god devops prinsipp å ikke commite direkte på master-branch, derfor konfiguerer jeg jobben 'build_docker_image' til å kun kjøres ved pull requester til master branch. Dette fungerer godt i dette prosjektet
da jeg slutter med å pushe direkte på master, og heller lager en feature-branch som så skal pull-requestes inn i master når en feature er ferdig.
Feature-branchen vil fortsatt kjøre jobben 'build' som sjekker om tester kjøres og programmet kompileres hver gang det pushes til feature-branch, men man vil spare run-time minutter i AWS. Da feature-branch slipper
å kjøre 'build_docker_image' på hver eneste push, men kun når det pulles på master-branch.


Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.

