package com.example.mobiletworeview

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobiletworeview.data.db.AppDataBase
import com.example.mobiletworeview.data.db.PostDao
import com.example.mobiletworeview.data.db.entity.PostEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PostDaoTest {


    private lateinit var postDao: PostDao
    private lateinit var db : AppDataBase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java)
            .build()
        postDao = db.postDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertPostAndGetPost() = runTest{
        val post = PostEntity(1,1,"titi","titi")
        postDao.addPost(post)

        val posts = postDao.getAllPost().first()

        Assert.assertEquals(post.id, posts.id)
    }

    @Test
    fun whenInsertPost_getPostById_getPost() = runTest{
        val post = PostEntity(22,1,"titi","titi")

        postDao.addPost(post)

        val obtained = postDao.getPostById(22)

        Assert.assertEquals(post.id , obtained.id)

    }

    @Test
    fun whenGetPostByIdFoundForIdNoExistedThrowIllegalStateException() = runTest{
        Assert.assertThrows(IllegalStateException::class.java){
            runTest {
                postDao.getPostById(21)
            }
        }

    }

    //DeleteAllPost test
    @Test
    fun deleteAllPost() = runTest{
        val post = PostEntity(1,1,"titi","titi")
        val post2 = PostEntity(2,1,"title","body")

        postDao.addPost(post)
        postDao.addPost(post2)

        postDao.deleteAllPost()

        val posts = postDao.getAllPost()

        Assert.assertEquals(0, posts.size)
    }

    //deletePost test
    @Test
    fun deletePost() = runTest{
        val post = PostEntity(1,1,"titi","titi")
        val post2 = PostEntity(2,1,"title","body")

        postDao.addPost(post)
        postDao.addPost(post2)

        postDao.deletePost(post)

        val posts = postDao.getAllPost()

        Assert.assertEquals(1, posts.size)
    }

    //deletePost throw exception test
    @Test
    fun deletePost_throwException() = runTest{
        val post = PostEntity(1,1,"titi","titi")
        val post2 = PostEntity(2,1,"title","body")

        postDao.addPost(post)
        postDao.addPost(post2)

        Assert.assertThrows(IllegalStateException::class.java){
            runTest {
                postDao.deletePost(PostEntity(3,1,"title","body"))
            }
        }
    }

}