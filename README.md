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
7. Scroll litt lenger ned og huk av 'Require status checks to pass before merging' og 'Require branches to be up to date before merging' og skriv inn 'build' i feltet.
![img_1.png](img_1.png)
8. Ved å gjøre dette krever man at for at pull-requesten skal kunne merges i master-branch er status sjekkene fra feature-branchen er nødt til å være vellykkede.

### Del 3 - Docker
#### oppgave 1.
#### oppgave 2.
#### oppgave 3.
Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.

