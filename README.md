# controleveiculo

Link para Testar: https://controle-veiculo-api.herokuapp.com/swagger-ui.html

![image](https://user-images.githubusercontent.com/47534815/123556491-ed6bda00-d761-11eb-90cb-c075b7c10965.png)

<b>Na Classe UsuarioController tem dois EndPoints que são:</b>

buscarUsuarioPorId: Onde conseguimos buscar um usuario ja cadastrado na nossa base de dados com todos os seus veiculos.

salvar: Onde conseguimos salvar um usuário na base de dados.</br></br>

<b>Na Classe VeiculoController temos cinco EndPoints que são:</b>
 
<b>getVeiculo:</b> Onde conseguimos buscar um veiculo junto a API da FIPE, utilizando spring-cloud-feign.

<b>save:</b> Onde conseguimos cadastrar um novo veiculo, precisamos passar corretamente uma marca, um modelo e um ano todos existentes na tabela FIPE, assim o veiculo vai ser salvo na nossa base de dados e automaticamente vai salvar o valor correspondente.

<b>getMarca:</b> Caso não saiba a marca consulte primeiro o EndPoint getVeiculo depois copie e cole o id da sua marca neste EndPoint para obter todos os tipos de veiculos desta marca.

![marca](https://user-images.githubusercontent.com/47534815/123643986-57848d80-d7fb-11eb-8a6f-6c5a70d1c2db.png)</br></br>


<b>getModelo:</b> Neste EndPoint você precisa informar alem do id da marca e o id do modelo que vai obter no EndPoint getMarca.
![modelo](https://user-images.githubusercontent.com/47534815/123644478-cc57c780-d7fb-11eb-933a-e1c63bda07f4.png)


<b>getVeiculo:</b> Neste EndPoint você precisa informar o id da marca, modelo e ano que vai obter através do EndPoint getModelo.
