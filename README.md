# App Pokedex Versao 2
<p>Projeto pessoal desenvolvido para fins de estudo de mobile com aplicação da linguagem kotlin</p>

<h2>Informações do projeto</h2>
<p>O projeto consiste em desenvolver um aplicativo que seja capaz de listar todos os pokemons, mostrar detalhes de um pokemon específico, classificar pokemons como favoritos, remover a classificação de favoritos e consultar uma lista de pokemons favoritos</p>

<h2>Tecnolgias e recursos utilizados para o desenvolvimento</h2>
<li>Retrofit para realizar a requisição da API</li>
<li>converter-gson para atividades de serialização de objetos</li>
<li>Glide para carregar as imagens</li>
<li>Aplicação de fragments para reaproveitamento dos objetos visuais</li>
<li>Tratativa dos erros de API</li>
<li>Arquitetura MVVM</li>
<li>Boas práticas com aplicação do Ktlint</li>
<li>Acessibilidade, utilizar https://developer.android.com/guide/topics/ui/accessibility/principles para
referência ou https://guia-wcag.com/ ;
<li>Suporte versão do Android 12;</li>
<li>Modularização (a fazer);</li>
<li>Testes unitários em JUnit (a fazer);</li>

<h2>As características funcionais e não funcionais do app consistem em:</h2>
<li>Lista de pokemons com scrolling infinito;</li>
<li>Adicionar um pokemon aos favoritos;</li>
<li>Controle de estado (se um pokemon foi adicionado aos favoritos ou não);</li>
<li>Indicar como pokemon favorito logo na tela de listagem dos pokemons;</li>
<li>Aplicar o conceito de modularização no aplicativo, importando/incluindo um projeto externo como uma library no Gradle.(a fazer!)</li>
<li> Deve existir uma tela padrão para erro com a opção de “tentar novamente”;</li>
<li>Para favoritos deverá ser utilizado algum meio de persistência local como por exemplo, Shared
preferences (não salvando toda resposta da api, apenas chave valor para marcar um pokemon como
favorito)</li>


<h2>Fluxo principal - Listagem de pokemons</h2>
<p float="left">
<img src="https://user-images.githubusercontent.com/103140224/175982436-59cfa1db-b738-4635-a910-14a43a6b4c12.png" width="300"/>
<img src="https://user-images.githubusercontent.com/103140224/175982456-88883704-dcee-453a-9ee7-00f1c6cd5abd.png" width="300"/>
<img src="https://user-images.githubusercontent.com/103140224/175982471-7aac06a7-2ff1-4b01-8cb6-b7a4b4b6d939.png" width="300"/>
</p>


<h2>Aplicação da acessibilidade</h2>
<li>Adição de etiquetas com contentDescription</li>
<li>Elementos editáveis aplicação android:hint e android:labelFor</li>
<li>Grupos de conteúdo relacionado aplicação do android:screenReaderFocusable do objeto de contêiner como true e o atributo android:focusable de cada objeto interno como false.</li>
