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

        // INSERT DATA - User
        fun InsertNewUser(ctx: Context, name: String, email: String, password: String) {

            var id = userList.last().id + 1
            var temp = User(id, name, email, password)
//            var id = 1
//            if (userList.size > 0) {
//                id = userList.list ..
//            }
            userList.add(temp)

//            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
//            val joinDateStr = dateFormat.format(joinDate)

            // INSERT IN SQLite
            var helper = Helper(ctx)
            var db = helper.writableDatabase
            db.execSQL(
                "INSERT INTO user(name, password)" +
                        "VALUES" + "('"+name+"', '"+password+"')"
            )
        }

        var LOGGED_IN_USER: User? = null
        fun login(name: String, password: String) {
            // CTRL + J
            for (user in userList) {
                if (user.name == name && user.password == password) {
                    LOGGED_IN_USER = user
                    return
                }
            }
        }

    }
}