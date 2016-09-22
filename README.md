Analise de desempenho de banco de dados com indices e sem indices.
Com objetivos de aquisição da aprovação na disciplina de Banco de Dados II.

1) Criar duas tabela iguais, com colunas quaisquers. Em uma tabela incrementar indices, na outra não incluir indices;

2) Fazer a inserção de 10k de registros, após realização dos próximos passos fazer testes com 100k de registros, nas tabelas criadas (necessário que fiquem com os mesmos registros);

3) Fazer uma consulta dos registros inseridos, em ambas as tabelas;

4) Analisar o tempo para realizar cada consulta em cada uma das tabelas;

Os tempos calculados serão utilizados como base para defesa do material em sala de aula;

Arquivos com registros foram baixados de: 
http://research.microsoft.com/apps/pubs/?id=152883

#Scripts de criação:

```SQL

create database oficial_i;

create table trajectory ( 
   id int unsigned not null auto_increment, 
   taxi_id int,
   when_ocurrs date, 
   longitude varchar(15), 
   latitude varchar(15), 
   primary key(id));

create table trajectory_w_index ( 
   id int unsigned not null auto_increment, 
   taxi_id int,
   when_ocurrs date, 
   longitude varchar(15), 
   latitude varchar(15), 
   primary key(id)); 

alter table trajectory_w_index
add index (longitude, latitude);

```
