import os.path

import mysql.connector as connection
from json import load

db = connection.connect(host='localhost', database='dataset',
                        user='root', password='root')

cursor = db.cursor()

def sqlInsertDados(nome, tempo):

        valores = (nome, tempo)
        query = f'INSERT INTO dados (nome, tempo) VALUES' \
                f'(%s, %s)'

        cursor.execute(query, valores)
        db.commit()

def sqlInsertImages(imagemPath, imagemNome):

    valores = (imagemPath, imagemNome)
    query = f"UPDATE dados SET imagem = %s" \
            f"WHERE nome = %s"

    print(imagemNome)

    cursor.execute(query, valores)
    db.commit()

def openDados(caminhoDados):
    path = open(caminhoDados)
    dado = load(path)
    for i in dado:
        sqlInsertDados(i['name'], i['attributes']['weather'])
    path.close()

def openImagens(caminhoImagens):
    for arquivo in os.listdir(caminhoImagens):
        with open(os.path.join(caminhoImagens, arquivo)) as arq:
            sqlInsertImages(arq.name, arq.name.split('/')[4])

if __name__ == "__main__":
    """
    openDados(r'C:/dataset/json/det_train.json')
    openDados(r'C:/dataset/json/det_val.json')
    openImagens(r'C:/dataset/imgs/val/')
    openImagens(r'C:/dataset/imgs/train/')
    """