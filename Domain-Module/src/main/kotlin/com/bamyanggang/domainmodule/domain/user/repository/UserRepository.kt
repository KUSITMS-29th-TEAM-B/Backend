package com.bamyanggang.domainmodule.domain.user.repository

import com.bamyanggang.domainmodule.domain.user.aggregate.User

interface UserRepository {

 fun save(user: User)

}