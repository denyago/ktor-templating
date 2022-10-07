package name.denyago.blog.data

class Post(val id: PostId, val author: Author, val title: Title, val content: PostContent, val comments: List<Comment>)
