from flask import Flask,redirect,flash,session,jsonify,request

from MySQLdb import IntegrityError
from flask import url_for
from passlib.hash import sha256_crypt
from flask_mysqldb import MySQL
from wtforms import Form, BooleanField, TextField, PasswordField, validators,StringField
from MySQLdb import escape_string as thwart
import gc

import logging
import json
import re

app = Flask(__name__)
try:
    mysql = MySQL()
    app.config['MYSQL_USER'] = 'root'
    app.config['MYSQL_PASSWORD'] = 'Hujingxian122811'
    app.config['MYSQL_DB'] = 'Ece651'
    app.config['MYSQL_HOST'] = 'localhost'
    mysql.init_app(app)
except Exception as e:
    logging.log(str(e))




def dbInit():
    query = ("create table if not exists `users` ("
             "userName varchar(100) primary key,"
             "password varchar(50),"
             "email varchar(100))"
             )
    conn = mysql.connection
    cur = conn.cursor()
    cur.execute(query)

@app.route('/login',methods=['POST'])
def logIn():
    dbInit()

    if request.method =='POST':

        logging.getLogger("Receiving user logIn request")

        userInfo = request.json
        userName = userInfo['userName']
        password = userInfo['userPassword']
        try:
            conn = mysql.connection
            cur = conn.cursor()
            cur.execute("SELECT userName,password FROM users WHERE userName = %s AND password = %s", (userName, password))
            if not cur.rowcount:
                return jsonify("Error! Dismatch of userName and password")
            else:
                logging.getLogger(userName + "successfully logged in")
                return jsonify("success")  # TBD

        except Exception as e:
            return jsonify("Error!"+str(e))


@app.route('/register',methods=['POST'])
def register():
    dbInit()
    if(request.method == 'POST'):

        logging.getLogger("Receiving new sign up request")

        userInfo = request.json
        userName = userInfo['userName']
        userEmail = userInfo['userEmail']
        #password = sha256_crypt.encrypt(userInfo['userPassword'])
        password = userInfo['userPassword']
        conn = mysql.connection
        cur = conn.cursor()
        try:
            query = "INSERT INTO users (userName, password, email) VALUES (%s, %s, %s)"
            cur.execute(query,(userName,password,userEmail))
            logging.getLogger("valid new user")
            conn.commit()
            res = "success"
            return jsonify(res)

        except IntegrityError as error:
            conn.rollback()
            return jsonify("Username already exists")

        except:
            return jsonify("System error")

if __name__ == '__main__':
    app.run(host='192.168.5.4', port=5000)



