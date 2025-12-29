import { Router } from 'express';
import Post from '../../models/post.js';
const router = Router();
router.get('/api/post/show/:id', async (req, res, next) => {
    const postId = req.params.id;
    try {
        const foundPost = await Post.findOne({ _id: postId }).populate('comments');
        res.status(200).send(foundPost);
    }
    catch (err) {
        const error = new Error('post cannot be fetched');
    }
});
router.get('/api/post/show', async (req, res, next) => {
    try {
        const allPosts = await Post.find();
        res.status(200).send(allPosts);
    }
    catch (err) {
        const error = new Error('posts cannot be fetched');
        next(error);
    }
});
export { router as showPostRouter };
//# sourceMappingURL=show.js.map