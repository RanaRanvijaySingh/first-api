package com.firstapi.firstapi.user

import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class UserDaoService {
    val users = ArrayList<User>()
    private var userCount: Int = 3

    init {
        users.add(User(1, "AAA", Date()))
        users.add(User(2, "BBB", Date()))
        users.add(User(3, "CCC", Date()))
    }

    fun findAll(): List<User> {
        return users
    }

    fun save(user: User): User {
        if (user.id == null) {
            user.id = ++userCount
        }
        users.add(user)
        return user
    }

    fun findOne(id: Int): User? {
        for (i in users) {
            if (i.id == id) {
                return i
            }
        }
        return null
    }

    fun deleteById(id: Int): User? {
        val i = users.iterator()
        while (i.hasNext()) {
            val user = i.next()
            if (user.id == id) {
                i.remove()
                return user
            }
        }
        return null
    }
}