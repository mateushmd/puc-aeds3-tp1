# Pucflix

O Pucflix é um pequeno banco de dados em arquivo que permite o gerenciamento, ou seja, a criação, exclusão, atualização de atributos e busca de séries e os elementos que a compõem, como episódios.

O sistema gerencia os relacionamentos entre os registros automaticamente, como a relação entre episódios e séries, além de manter a integridade dos dados regrando as operações de acordo com o estado dos relacionamentos.

## Participantes

- Diego Feitosa Ferreira dos Santos
- Felipe Guerzoni Martins Flôres Maia
- Guilherme Soares Silva
- Mateus Henrique Medeiros Diniz 

## Estrutura 

### Entidades

`Episode`

Representa o registro episódio. Contém os atributos, getters, setters e métodos para conversão entre objeto e binário.

`Show`

Representa o registro série. Assim como o episódio, contém atributos, getters e setters e métodos de conversão entre objeto e binário.

### Modelo

`ShowFile`

Classe da camada de modelo para séries. Contém os métodos do CRUD e o índice para o par nome-id, que ajuda na busca por séries pelo nome.

`ShowNameIdPair`

Par nome-id da série, utilizada em uma árvore B+ para busca de séries pelo nome.

`EpisodeFile`

Classe da camada de modelo para episódios. Contém os métodos do CRUD e um método que recupera todos os episódios de uma série ordenados por temporada, além do índice nome-id para busca por nome e o índice id-id para busca por série.

`EpisodeNameIdPair`

Par nome-id do episódio, utilizado em uma árvore B+ para busca de séries pelo nome.

### Visão

`Prompt`

Classe central da camada de visão. É responsável por exibir os prompts e ler a entrada, controlar o fluxo do programa e decidir qual visão vai ser utilizada. É possível acrescentar mais visões de forma escalável, uma vez que a classe ajusta dinamicamente o prompt para exibir todas as escolhas para visões e todas as opções de uma visão.

`View`

Classe abstrata molde para visões. Contém todos os métodos necessários para que uma visão possa ser utilizada pela classe `Prompt`, como o `getName()` para exibir a visão que está sendo utilizada no momento, o `getPrompt()` para recuperar as opções para serem impressas na tela, e o `eval()` para processar a entrada do usuário e executar o comando correspondente.

`ShowView`

Classe da camada de visão da série, implementação concreta da `View`. Realiza todo o pré-processamento, como a leitura dos inputs para criação, por exemplo, para executar todas as operações do CRUD além de exibir os episódios por temporada de uma série específica.

`EpisodeView`

Classe da camada de visão da série, implementação concreta da `View`. Realiza todo o pré-processamentopara executar todas as operações do CRUD.

## Experiência

O grupo buscou implementar todos os requisitos do sistema de forma organizada e escalável, de forma que ficasse fácil entender o código e realizar correções e aprimoramentos. As implementações foram relativamente fáceis, principalmente com as implementações prontas disponíveis para uso. Mesmo assim, a implementação do relacionamento 1:N foi um pouco problemática na medida que foi necessário entender a classe `ParIdId` e quais operações do banco de dados necessitavam de alteração para manter a integridade do relacionamento no arquivo. De forma geral, os resultados foram alcançados, e o grupo acredita que implementou uma boa solução para o problema.

- [x] As operações de inclusão, busca, alteração e exclusão de séries estão implementadas e funcionando corretamente? Sim
- [x] As operações de inclusão, busca, alteração e exclusão de episódios, por série, estão implementadas e funcionando corretamente? Sim
- [x] Essas operações usam a classe CRUD genérica para a construção do arquivo e as classes Tabela Hash Extensível e Árvore B+ como índices diretos e indiretos? Sim
- [x] O atributo de ID de série, como chave estrangeira, foi criado na classe de episódios? Sim
- [x] Há uma árvore B+ que registre o relacionamento 1:N entre episódios e séries? Sim
- [x] Há uma visualização das séries que mostre os episódios por temporada? Sim
- [x] A remoção de séries checa se há algum episódio vinculado a ela? Sim
- [x] A inclusão da série em um episódio se limita às séries existentes? Sim
- [x] O trabalho está funcionando corretamente? Sim
- [x] O trabalho está completo? Sim
- [x] O trabalho é original e não a cópia de um trabalho de outro grupo? Sim

## Compilação e execução
Para compilar e executar o programa, utilize o script correspondente ao seu sistema operacional:
- *Linux*: `build.sh`
- *Windows*: `build.bat`

Alternativamente, execute a seguinte sequência de comandos na pasta raíz do projeto:

Crie a pasta `/bin` na pasta raíz do projeto caso não exista ainda:

```sh
mkdir bin
```
Ainda na pasta raíz, execute a seguinte sequência de comandos:

```sh
cd src

javac -cp . -d ../bin pucflix/Main.java pucflix/aeds3/*.java pucflix/view/*.java pucflix/model/*.java pucflix/entity/*.java

cd ../bin

java -cp . pucflix.Main
```
