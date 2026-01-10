import { Router, Request, Response, NextFunction } from 'express';
import Comment from '../../models/comment.js';
import Post from '../../models/post.js';
import { BadRequestError } from '../../common/index.js';

const router = Router();

router.post('/api/comment/new/:postId', async (req: Request, res: Response, next: NextFunction) => {
    const { postId } = req.params;
    console.log(postId);
    if(!postId) {
        return next(new BadRequestError('postId is required!'));
    }
    const { userName, content } = req.body;

    if(!content) {
        return next(new BadRequestError('content is required!'));
    }

    const newComment = Comment.build({
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