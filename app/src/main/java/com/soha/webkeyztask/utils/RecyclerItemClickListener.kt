package com.soha.webkeyztask.utils

import com.soha.webkeyztask.data.models.Article

interface RecyclerItemClickListener {
   fun onArticleClick(article: Article)
}