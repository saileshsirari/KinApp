/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDetailFragmentTest {

    /* @Rule
     @JvmField
     val activityTestRule = ActivityTestRule(GardenActivity::class.java)

     @Before
     fun jumpToPlantDetailFragment() {
         activityTestRule.activity.apply {
             runOnUiThread {
                 val bundle = Bundle().apply { putString("plantId", testPlant.plantId) }
                 findNavController(R.id.nav_host).navigate(R.id.plant_detail_fragment, bundle)
             }
         }
     }

     @Ignore("Share button redesign pending")
     @Test
     fun testShareTextIntent() {
         val shareText = activityTestRule.activity.getString(
             R.string.share_text_plant,
             testPlant.name
         )

         Intents.init()
         onView(withId(R.id.action_share)).perform(click())
         intended(
             chooser(
                 allOf(
                     hasAction(Intent.ACTION_SEND),
                     hasType("text/plain"),
                     hasExtra(Intent.EXTRA_TEXT, shareText)
                 )
             )
         )
         Intents.release()

         // dismiss the Share Dialog
         InstrumentationRegistry.getInstrumentation()
             .uiAutomation
             .performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
     }*/
}
