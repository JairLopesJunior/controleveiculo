# controleveiculo

Link para Testar: https://controle-veiculo-api.herokuapp.com/swagger-ui.html

![image](https://user-images.githubusercontent.com/47534815/123556491-ed6bda00-d761-11eb-90cb-c075b7c10965.png)

<b>Na Classe UsuarioController tem dois EndPoints que são:</b>

<b>buscarUsuarioPorId:</b> Onde conseguimos buscar um usuario ja cadastrado na nossa base de dados com todos os seus veiculos.

<b>salvar:</b> Onde conseguimos salvar um usuário na base de dados.</br></br>

<b>Na Classe VeiculoController temos cinco EndPoints que são:</b>
 
<b>getVeiculo:</b> Onde conseguimos buscar um veiculo junto a API da FIPE, utilizando spring-cloud-feign.

<b>save:</b> Onde conseguimos cadastrar um novo veiculo, precisamos passar corretamente uma marca, um modelo e um ano todos existentes na tabela FIPE, assim o veiculo vai ser salvo na nossa base de dados e automaticamente vai salvar o valor correspondente.

<b>getMarca:</b> Caso não saiba a marca consulte primeiro o EndPoint getVeiculo depois copie e cole o id da sua marca neste EndPoint para obter todos os tipos de veiculos desta marca. Neste caso o <b>ID</b> da minha marca é <b>6</b>.

![marca](https://user-images.githubusercontent.com/47534815/123643986-57848d80-d7fb-11eb-8a6f-6c5a70d1c2db.png)</br></br>


<b>getModelo:</b> Neste EndPoint você precisa informar alem do id da marca e o id do modelo que vai obter no EndPoint getMarca. Neste caso o <b>ID</b> da minha marca é <b>6</b> e o <b>ID</b> do modelo é <b>43</b>.

![modelo](https://user-images.githubusercontent.com/47534815/123644478-cc57c780-d7fb-11eb-933a-e1c63bda07f4.png)


<b>getVeiculo:</b> Neste EndPoint você precisa informar o id da marca, modelo e ano que vai obter através do EndPoint getModelo. Neste caso o <b>ID</b> da minha marca é <b>6</b>, o <b>ID</b> do modelo é <b>43</b> e o ano é <b>1995-1</b>, o ano deverá ser informado no formato convencional <b>01/01/1995</b> neste caso, o dia não importa por que a API considera somente o mês e ano.

![ano](https://user-images.githubusercontent.com/47534815/123645161-77688100-d7fc-11eb-9b86-c6a48eff1c5b.png)

