import { Router, Request, Response, NextFunction } from 'express';
import Post from "../../models/post.js";
import { BadRequestError } from '../../common/index.js';

const router = Router();

router.delete('/api/post/delete/:id', async (req: Request, res: Response, next: NextFunction) => {
    const postId = req.params.id;
    if(!postId) {
        return next(new BadRequestError('postId is required!'));
    }
    try {
        await Post.findOneAndDelete({ _id: postId});
    } catch (err) {
        next(new Error('post cannot be updated!'));
    }
    res.status(204).send();
});

export { router as deletePostRouter };