import { Router, Request, Response, NextFunction } from 'express';
import Post from "../../models/post.js";

const router = Router();

router.delete('/api/post/delete/:id', async (req: Request, res: Response, next: NextFunction) => {
    const postId = req.params.id;
    if(!postId) {
        const error = new Error('postId is required') as CustomError;
        error.status = 400;
        next(error);
    }
    try {
        await Post.findOneAndDelete({ _id: postId});
    } catch (err) {
        const error = new Error('post cannot be deleted') as CustomError;
        error.status = 500;
        next(error);
    }
    res.status(204).send();
});

export { router as deletePostRouter };