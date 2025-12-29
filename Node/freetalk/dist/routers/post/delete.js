import { Router } from 'express';
import Post from "../../models/post.js";
const router = Router();
router.delete('/api/post/delete/:id', async (req, res, next) => {
    const postId = req.params.id;
    if (!postId) {
        const error = new Error('postId is required');
        error.status = 400;
        next(error);
    }
    try {
        await Post.findOneAndDelete({ _id: postId });
    }
    catch (err) {
        const error = new Error('post cannot be deleted');
        error.status = 500;
        next(error);
    }
    res.status(204).send();
});
export { router as deletePostRouter };
//# sourceMappingURL=delete.js.map