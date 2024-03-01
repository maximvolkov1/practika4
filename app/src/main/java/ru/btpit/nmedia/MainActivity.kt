package ru.btpit.nmedia

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.dto.Post


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Интересно знать!",
            content = "Спросите у любого, какое животное на нашей планете носит гордое звание царь зверей, и каждый из них ответит, что это лев. Именно эта большая кошка из рода пантер занимает столь высокий пост. Но почему именно лев – царь зверей? Лев – хищный представитель семейства Кошачьих, один из самых умных, быстрых и крупных животных. В этой статье мы расскажем почему лев – царь зверей.",
            published = "20 января в 12:12",
            likedByMe = false,
            sharedByMe = false,
            viewedByMe = false
        )
        findViewById<ImageButton>(R.id.like).setOnClickListener {
            if (it !is ImageButton) {
                return@setOnClickListener
            }
            it.setImageResource(R.drawable.ic_liked_24)
        }
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = formatCount(post.likes)
            shareCount.text = formatCount(post.shares)
            viewsCount.text = formatCount(post.viewes)
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            like?.setOnClickListener {
                if (post.likedByMe) {
                    post.likes--
                } else {
                    post.likes++
                }
                post.likedByMe = !post.likedByMe
                like.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
                likeCount.text = post.likes.toString()
                likeCount.text = formatCount(post.likes)
            }
                share?.setOnClickListener {
                    post.shares++
                    post.sharedByMe = !post.sharedByMe
                    shareCount.text = post.shares.toString()
                    shareCount.text = formatCount(post.shares)
                }
            }
        }
    }
fun formatCount(num: Int): String {
    return when {
        num >= 1_000_000 -> {
            val millionPart = num / 1_000_000
            val remainder = num % 1_000_000
            if (remainder == 0) {
                "$millionPart"
            } else {
                "$millionPart.${remainder / 100_000}M"
            }
        }
        num >= 1_000 -> {
            val numString = if (num % 1_000 == 0) "${num / 1_000}K" else "${num / 1_000}.${(num % 1_000) / 100}K"
            numString
        }
        num >= 10000 -> "${num / 1000}K"
        else -> num.toString()
    }
}



