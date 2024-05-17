package com.bamyanggang.apimodule.domain.tag.application.dto

import java.util.UUID

class CreateTag {
    data class Request(val name: String)
    data class Response(val id: UUID)
}