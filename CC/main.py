import os
from flask import Flask, jsonify, request

import mysql.connector
import pandas as pd
import numpy as np
import sys
import json

app = Flask(__name__)

@app.route("/", methods=['GET', 'POST'])
def api():
    return "server femeow jalan"

@app.route("/login", methods=['GET', 'POST'])
def login():

    if request.method == 'POST':

        json = request.json

       #database connect
        cnx = mysql.connector.connect(user='root', password='123', host='34.68.201.197', database='femeow')
        cursor = cnx.cursor()

        #query
        cursor.execute("select * from user;")
        row_headers=[x[0] for x in cursor.description]
        rv = cursor.fetchall()
        json_data = []
        for result in rv:
            json_data.append(dict(zip(row_headers,result)))
        cnx.close()

        #finding algorithm
        result = next((item for item in json_data if item["username"] == json['username'] and item["password"] == json['password']), None)
        if result != None:
            jsonResult = {
                "error" : False,
                "message" : "success",
                "loginResult" : result
            }
        else:
            jsonResult = {
                "error" : True,
                "message" : "failed",
            }
        return jsonify(jsonResult)

    else:
        jsonResult = {
                "error" : True,
                "message" : "failed",
            }
        return (jsonResult)

@app.route("/forum", methods=['GET', 'POST'])
def forum():
    #database connect
    cnx = mysql.connector.connect(user='root', password='123', host='34.68.201.197', database='femeow')
    cursor = cnx.cursor()

    if request.method == 'POST':
        content_type = request.headers.get('Content-Type')
        if (content_type == 'application/json'):
            json = request.json

            #query
            query = "INSERT INTO `forum` (`idPost`, `title`, `body`, `haveImage`, `imageBase64`, `breed`, `dateCreated`, `createdBy`) VALUES (NULL, '{}', '{}', '{}', '{}', '{}', '{}', '{}')".format(json['title'],json['body'],json['haveImage'],json['imageBase64'],json['breed'],json['dateCreated'],json['createdBy'])
            cursor.execute(query)
            result = cursor.fetchone()
            cnx.commit()
            cnx.close()

            result = {
                "error" : False,
                "message" : "success",
            }
            return result
        else:
            return 'Content-Type not supported!'
    else:
       #query
        cursor.execute("SELECT f.idPost, f.title, f.body, f.haveImage, f.imageBase64, f.breed, f.dateCreated, f.createdBy, u.nama_lengkap FROM forum f LEFT OUTER JOIN user u ON f.createdBy=u.username;")
        row_headers=[x[0] for x in cursor.description]
        rv = cursor.fetchall()
        json_data = []
        for result in rv:
            json_data.append(dict(zip(row_headers,result)))
        cnx.close()

        jsonResult = {
                "error" : False,
                "message" : "success",
                "getForumResult" : json_data
            }


        return jsonify(jsonResult)
    
@app.route("/comment", methods=['GET', 'POST'])
def comment():
    #database connect
    cnx = mysql.connector.connect(user='root', password='123', host='34.68.201.197', database='femeow')
    cursor = cnx.cursor()

    if request.method == 'POST':
        content_type = request.headers.get('Content-Type')
        if (content_type == 'application/json'):
            json = request.json

            #query
            query = "INSERT INTO `comment` (`idComment`, `dateCreated`, `body`, `createdBy`, `idPost`) VALUES (NULL, '{}', '{}', '{}', '{}')".format(json['dateCreated'],json['body'],json['createdBy'],json['idPost'])
            cursor.execute(query)
            result = cursor.fetchone()
            cnx.commit()
            cnx.close()

            result = {
                "error" : False,
                "message" : "success",
            }
            return result
        else:
            return 'Content-Type not supported!'
    else:

        paramIdPost = str(request.args.get("idPost"))
       #query
        query="SELECT c.idPost, c.body, c.dateCreated, c.createdBy, u.nama_lengkap FROM comment c LEFT OUTER JOIN user u ON c.createdBy=u.username WHERE c.idPost = {}".format(paramIdPost)
        cursor.execute(query)
        row_headers=[x[0] for x in cursor.description]
        rv = cursor.fetchall()
        json_data = []
        for result in rv:
            json_data.append(dict(zip(row_headers,result)))
        cnx.close()

        jsonResult = {
                "error" : False,
                "message" : "success",
                "getCommentResult" : json_data
            }


        return jsonify(jsonResult)

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
