import { Router, Request, Response, NextFunction } from 'express';
import Post from '../../models/post.js';

const router = Router();

router.get('/api/post/show/:id', async (req: Request, res: Response, next: NextFunction) => {
    const postId = req.params.id;
    
    try {
        const foundPost = await Post.findOne({ _id: postId }).populate('comments');
        res.status(200).send(foundPost);
    } catch(err) {
        const error = new Error('post cannot be fetched');
    }
});

router.get('/api/post/show', async (req: Request, res: Response, next: NextFunction) => {
    try {
        const allPosts = await Post.find();
        res.status(200).send(allPosts);
    } catch(err) {
        const error = new Error('posts cannot be fetched');
        next(error);
    }
});
export { router as showPostRouter };