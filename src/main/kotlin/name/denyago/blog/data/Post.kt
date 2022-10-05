package name.denyago.blog.data

class Post(val author: Author, val title: Title, val content: PostContent, val comments: List<Comment>)
