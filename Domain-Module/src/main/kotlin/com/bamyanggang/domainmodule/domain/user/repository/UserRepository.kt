package com.bamyanggang.domainmodule.domain.user.repository

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import java.util.UUID

interface UserRepository {

 fun save(user: User)

 fun findBySocialId(socialId: String): User?

 fun findById(userId: UUID): User

 fun existsBySocialId(socialId: String): Boolean

}
