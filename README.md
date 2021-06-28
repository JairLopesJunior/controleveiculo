# controleveiculo

Link para Testar: https://controle-veiculo-api.herokuapp.com/swagger-ui.html

![image](https://user-images.githubusercontent.com/47534815/123556491-ed6bda00-d761-11eb-90cb-c075b7c10965.png)

<b>Na Classe UsuarioController tem dois EndPoints que são:</b>

buscarUsuarioPorId: Onde conseguimos buscar um usuario ja cadastrado na nossa base de dados com todos os seus veiculos.

salvar: Onde conseguimos salvar um usuário na base de dados.</br></br>

<b>Na Classe VeiculoController temos cinco EndPoints que são:</b>
 
getVeiculo: Onde conseguimos buscar um veiculo junto a API da FIPE, utilizando spring-cloud-feign.

save: Onde conseguimos cadastrar um novo veiculo, precisamos passar corretamente uma marca, um modelo e um ano todos existentes na tabela FIPE, assim o veiculo vai ser salvo na nossa base de dados e automaticamente vai salvar o valor correspondente.

getMarca: Caso não saiba a marca consulte primeiro o EndPoint getVeiculo depois copie e cole o id da sua marca neste EndPoint para obter todos os tipos de veiculos desta marca.

getModelo: Neste EndPoint você precisa informar alem do id da marca o modelo que vai obter no EndPoint getMarca.

getVeiculo: Neste EndPoint você precisa informar o id da marca, modelo e ano que vai obter através do EEndPoint getModelo.
