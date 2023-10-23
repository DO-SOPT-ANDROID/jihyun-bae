package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.Profile

interface DummyRepository {
    fun getMockProfileList(): List<Profile>
}