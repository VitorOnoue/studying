import { Router } from 'express';
import Post from '../../models/post.js';
const router = Router();
router.post('/api/post/new', async (req, res, next) => {
    const { title, content } = req.body;
    if (!title || !content) {
        const error = new Error('title and content are required!');
        error.status = 400;
        next(error);
    }
    const newPost = new Post({
        title,
        content
    });
    await newPost.save();
    res.status(201).send(newPost);
});
export { router as newPostRouter };
//# sourceMappingURL=new.js.map