import os
import mysql.connector
from flask import Flask, jsonify, request


app = Flask(__name__)


@app.route("/login", methods=['GET', 'POST'])
def getLogin():
    data = [{'username': 'danala04', 'password': 123},
            {'username': 'iik1412', 'password': 123}]
    username = request.args.get('username')
    password = request.args.get('password')
    if username=="danala04":
        return jsonify(data) 
    else:
        return "Akun tidak ada", 403
    



if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
