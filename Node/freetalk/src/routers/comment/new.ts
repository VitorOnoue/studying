import { Router, Request, Response, NextFunction } from 'express';
import Comment from '../../models/comment.js';
import Post from '../../models/post.js';

const router = Router();

router.post('/api/comment/new/:postId', async (req: Request, res: Response, next: NextFunction) => {
    const { postId } = req.params;
    console.log(postId);
    if(!postId) {
        const error = new Error('postId is required') as CustomError;
        error.status = 400;
        next(error);
    }
    const { userName, content } = req.body;

    if(!content) {
        const error = new Error('content is required') as CustomError;
        error.status = 400;
        next(error);
    }

    const newComment = new Comment({
        userName: userName ? userName : 'you dont know who i am',
        content: content
    });
    
    await newComment.save();

    const updatedPost = await Post.findOneAndUpdate(
        { _id: postId },
        { $push: { comments: newComment } },
        { new: true }
    );

    res.status(201).send(updatedPost);
});

export { router as newCommentRouter };