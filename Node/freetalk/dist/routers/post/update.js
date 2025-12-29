import { Router } from 'express';
import Post from "../../models/post.js";
const router = Router();
router.post('/api/post/update/:id', async (req, res, next) => {
    const postId = req.params.id;
    const { title, content } = req.body;
    if (!postId) {
        const error = new Error('postId is required');
        error.status = 400;
        next(error);
    }
    let updatedPost;
    try {
        updatedPost = await Post.findOneAndUpdate({ _id: postId }, { $set: { content, title } }, { new: true });
    }
    catch (err) {
        const error = new Error('post cannot be updated');
        error.status = 500;
        next(error);
    }
    res.status(200).send(updatedPost);
});
export { router as updatePostRouter };
//# sourceMappingURL=update.js.map