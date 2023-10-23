package org.sopt.dosopttemplate.data.datasource.local.DataSource

import org.sopt.dosopttemplate.data.model.local.Profile

interface DummyDataSource {
    val mockProfileList: List<Profile>
}