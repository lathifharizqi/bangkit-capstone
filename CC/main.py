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
    return "server jalan"

@app.route("/login", methods=['GET', 'POST'])
def login():
    paramUsername = str(request.args.get("username"))
    paramPass = str(request.args.get("password"))

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
    result = next((item for item in json_data if item["username"] == paramUsername and item["password"] == paramPass), None)
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

@app.route("/forum", methods=['GET', 'POST'])
def forum():
    #database connect
    cnx = mysql.connector.connect(user='root', password='123', host='34.68.201.197', database='femeow')
    cursor = cnx.cursor()

    if request.method == 'POST':
        return "tes"
    else:
       #query
        cursor.execute("select * from forum;")
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
    


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
