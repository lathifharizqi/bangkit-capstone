import os
from flask import Flask, jsonify, request

import mysql.connector
import pandas as pd
import numpy as np
import sys

app = Flask(__name__)

@app.route("/login", methods=['GET', 'POST'])
def getLogin():
    data = [{'username': 'danala04', 'password': 123, 'name': 'Daffa Nabil Libriana'}]
    username = request.args.get('username')
    password = request.args.get('password')
    if username=="danala04" and password=="123":
        return jsonify(data) 
    else:
        return "Akun tidak ada", 403

@app.route("/getlogin", methods=['GET', 'POST'])
def login():
    cnx = mysql.connector.connect(user='root', password='123', host='34.68.201.197', database='femeow')
    cursor = cnx.cursor()
    cursor.execute("select * from user;")
    result = cursor.fetchall()
    cnx.close()
    return (result[1][0])   



if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
