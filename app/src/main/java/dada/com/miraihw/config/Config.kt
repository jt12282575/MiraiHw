package dada.com.miraihw.config

class Config {
    companion object{
        fun showStaffTag(siteAdmin:Boolean):Boolean = !siteAdmin
    }
}