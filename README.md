
![Modelo Conceitual](https://github.com/RodriguesLucas/maquinaNorma/blob/main/ASSETS/Unisc.png)

# Computabilidade – Trabalho 1 – Simulador máquina Norma
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/RodriguesLucas/maquinaNorma/blob/main/LICENSE) 

NOME: Gabriel Lopes, Henyo Nunes e Lucas Rodrigues  
DATA: 12/12/2022  


# Tal como a máquina norma, nosso simulador possibilita:
 - Teste: x_zero: Testa se o registrador x vale zero. 
 - Operações: o ad_x: Incrementa o registrador x.
 - Operações: o sub_x: Decrementa o registrador x. 

# Descrição do funcionamento:
- O algoritmo lê as instruções 
- Faz a separação dos rótulos, comandos e valores.
- realiza as operações, testagem, adição e subtração
- Ao chegar ao fim da instrução, realiza sua parada
.


# Estrutura de dados utilizadas:
Neste simulador, utilizamos a estrutura de lista, e utilizamos a linguagem Java.

# Lógica do funcionamento:
- O algoritmo lê as instruções por meio de um documento, já preenchido previamente pelo usuário.
- Faz a separação dos rótulos, comandos e valores, por meio da comparação do comando, salvando, após um número x de casas, o valor ou o comando, para ser utilizado posteriormente para o funcionamento do algoritmo.
- Por meio dos dados separados, realiza as operações, testagem, adição e subtração
- Possibilita a utilização de macros pré definidas para realização de algumas operações, definidas pelo usuário.
- Ao chegar ao fim da instrução, realiza sua parada.
# A lógica do programa foi separada basicamente em cinco funções:
- getRegistradores() - Responsável por criar uma lista de objeto (Registrador), com todos os registradores definidos pelo usuário no arquivo.
- getInstrucoes() - Responsável por pegar todas as instruções do arquivo, transformando-a em um objeto (Rótulo), que posteriormente será utilizado na lógica de execução.
- getMacros() - Método que percorre todos os arquivos de macros criando uma lista com objeto (Macro).
- executa() - Percorre a lista de instruções (Rótulos), fazendo as operações correspondentes a cada posição da lista. Tendo a parada definida pelo usuário no código de execução.
- executaMacro() - Percorre a lista de Macros criadas anteriormente, e chama o método executa().
# Prints das Telas:
# Sem Macro			     
![Modelo Conceitual](https://github.com/RodriguesLucas/maquinaNorma/blob/main/ASSETS/SemMacro.png)

# Com Macro
![Modelo Conceitual](https://github.com/RodriguesLucas/maquinaNorma/blob/main/ASSETS/ComMacro.png)
