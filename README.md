![Badge](https://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=7ab317&style=flat-square)
![Badge](https://img.shields.io/github/forks/inverter-systems/microvix)
![Badge](https://img.shields.io/github/stars/inverter-systems/microvix)
![Badge](https://img.shields.io/github/license/inverter-systems/microvix)
![Badge](https://img.shields.io/github/issues/inverter-systems/microvix)
![Badge](https://img.shields.io/static/v1?label=Inverter&message=Systems%20%C2%AE&color=0d6759&style=flat-square&logo=dblp)


# Integração Microvix / N&L Gestão

Sistema desenvolvido para integrar o ERP Microvix da empresa Linx com o ERP N&L Gestão da empresa N&L Informática para uso da Top Internacional.

O sistema foi desenvolvido utilizando a linguagem java e o framework Spring. Foi feito também o uso da biblioteca [java_nfe](https://github.com/Samuel-Oliveira/Java_NFe) do Samuel Oliveira, para ler o xml da nota fiscal.

O sistema ira integrar dados nos dois sentidos, levando as informações de pessoas e produtos do sistema N&L Gestão para o sistema Microvix e retornando as notas fiscais emitidas no Microvix para o N&L Gestão.

O sistema interage com os web services da Linx e com o banco de dados do N&L Gestão, para a troca de informações entre os sistemas.

## Funcionalidades do projeto

- `FR-001`: Interface que permita a visualização, seleção e envio de informações para o Microvix, dos os cadastros de Setores Comerciais, Linha de Produtos, Marcas, Código CEST, Código NCM, Código de Barras, Clientes, Fornecedores e Produtos, existentes no sistema N&L Gestão. 
- `FR-002`: Interface que permita a visualização, seleção e envio de informações para o N&L Gestão, das movimentações de notas fiscais ocorridas no Microvix.
- `FR-003`: Controle de acesso.
- `FR-004`: Agendamento de execuções automáticas.
- `FR-005`: Registros e controle de execuções de integração(log).

## Intalação

Desativar o monitoramento do arquivo e configira-lo de acordo com seu ambiente.

```bash
$ git update-index --assume-unchanged src/main/resources/application.properties 
