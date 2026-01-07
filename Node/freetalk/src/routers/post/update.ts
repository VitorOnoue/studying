import { Router, Request, Response, NextFunction } from 'express';
import Post from "../../models/post.js";
import { BadRequestError } from '../../common/index.js';

const router = Router();

router.post('/api/post/update/:id', async (req: Request, res: Response, next: NextFunction) => {
    const postId = req.params.id;
    const { title, content } = req.body;

    if (!postId) {
        return next(new BadRequestError('post id and comment id are required!'));
    }
    let updatedPost;
    try {
        updatedPost = await Post.findOneAndUpdate({ _id: postId }, { $set: { content, title } }, { new: true });
    } catch (err) {
        next(new Error('post cannot be updated!'));
    }

    res.status(200).send(updatedPost);
});

export { router as updatePostRouter };