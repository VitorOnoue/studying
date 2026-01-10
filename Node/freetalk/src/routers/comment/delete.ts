import { Router, Request, Response, NextFunction } from 'express';
import Comment from "../../models/comment.js";
import Post from "../../models/post.js";
import { BadRequestError } from '../../common/index.js';

const router = Router();

router.delete('/api/comment/:commentId/delete/:postId', async (req: Request, res: Response, next: NextFunction) => {
    const commentId = req.params.commentId;
    const postId = req.params.postId;

    if (!postId || !commentId) {
        return next(new BadRequestError('post id and comment id are required!'));
    }
    try {
        await Comment.findOneAndDelete({ _id: commentId });
    } catch (err) {
        next(new Error('comment cannot be deleted'));
    }

    const updatedPost = await Post.findOneAndUpdate(
        { _id: postId },
        { $pull: { comments: commentId } },
        { new: true }
    )
    if (!updatedPost) {
        return next(new Error());
    }

    res.status(200).send(updatedPost);
});

export { router as deleteCommentRouter };