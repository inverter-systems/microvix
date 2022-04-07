# Integração Microvix / N&L Gestão

Sistema desenvolvido para integrar o ERP Microvix da empresa Linx com o ERP N&L Gestão da empresa N&L Informática para uso da Top Internacional.

O sistema foi desenvolvido utilizando a linguagem java e o framework Spring. Foi feito também o uso da biblioteca [java_nfe](https://github.com/Samuel-Oliveira/Java_NFe) do Samuel Oliveira, para ler o xml da nota fiscal.

O sistema ira integrar dados nos dois sentidos, levando as informações de pessoas e produtos do sistema N&L Gestão para o sistema Microvix e retornando as notas fiscais emitidas no Microvix para o N&L Gestão.

O sistema interage com os web services da Linx e com o banco de dados do N&L Gestão, para a troca de informações entre os sistemas.

## Funcionalidades do projeto

- `Feature 1`: Interface que permita a visualização, seleção e envio de informações para o Microvix, para os cadastros de: Setores Comerciais, Linha de Produtos, Marcas, Código CEST, Código NCM, 				  Código de Barras, Clientes, Fornecedores e Produtos. 
- `Feature 2`: Registros e controle de execuções de integração(log)
- `Feature 3`: Controle de acesso
- `Feature 4`: Agendamento de execuções automáticas

## Intalação

```bash
# Desativar o monitoramento do arquivo e configira-lo de acordo com seu ambiente.
$ git update-index --assume-unchanged src/main/resources/application.properties 
