package com.example.workit.utils

import android.content.Context
import com.example.workit.model.User
import java.text.SimpleDateFormat
import com.example.workit.utils.Helper


class DB {
    // VARIABLE STATIC GLOBAL
    companion object {
        var userList = mutableListOf<User>()

        var HAS_SYNC = false

        fun syncData(ctx: Context) {
            if (HAS_SYNC) return

            // QUERY TABLE
            var helper = Helper(ctx)
            var db = helper.readableDatabase
            var cursor = db.rawQuery("SELECT * FROM user", null)

            while (cursor.moveToNext()) {
                var id = cursor.getInt(0)
                var name = cursor.getString(1)
                var email = cursor.getString(2)
                var password = cursor.getString(3)
//                var memberType = cursor.getString(3)
//                var joinDate = cursor.getString(4)
//                var joinDateReal = if (joinDate != null) SimpleDateFormat("yyyy-MM-dd").parse(joinDate) else null
                var temp = User(id, name, email, password)
                userList.add(temp)
            }
            cursor.close()

            HAS_SYNC = true
        }

        fun getUserByEmail(email: String): User? {
            return userList.find { it.email == email }
        }


        fun InsertNewUser(ctx: Context, name: String, email: String, password: String) {

            val id = if (userList.isNotEmpty()) userList.last().id + 1 else 1
            val temp = User(id, name, email, password)
            userList.add(temp)

            val helper = Helper(ctx)
            val db = helper.writableDatabase
            db.execSQL(
                "INSERT INTO user(username, email, password, member_type) VALUES('$name', '$email', '$password', '')"
            )
        }

        var LOGGED_IN_USER: User? = null
        fun login(email: String, password: String) {
            for (user in userList) {
                if (user.email == email && user.password == password) {
                    LOGGED_IN_USER = user
                    return
                }
            }
            LOGGED_IN_USER = null
        }

    }
}
