package com.example.data.user.repositories.local

interface UserDataBase {
    fun userDao(): UserDao
}