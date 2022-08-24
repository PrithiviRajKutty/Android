package com.example.recycler_view_project


data class Model(val image: String, val description: String)




object MockList {

    fun getModel(image: ArrayList<String>, name: ArrayList<String>): List<Model> {
        val itemList: ArrayList<Model> = ArrayList()
    for (i in 0 until name.lastIndex){
        val itemModel1 = Model(
            image[i],
            name[i],
        )

        itemList.add(itemModel1)
    }




        return itemList
    }

}