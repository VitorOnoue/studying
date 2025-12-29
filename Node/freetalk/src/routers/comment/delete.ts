import { Router, Request, Response, NextFunction } from 'express';
import Comment from "../../models/comment.js";
import Post from "../../models/post.js";

const router = Router();

router.delete('/api/comment/:commentId/delete/:postId', async (req: Request, res: Response, next: NextFunction) => {
    const commentId = req.params.commentId;
    const postId = req.params.postId;

    if (!postId || !commentId) {
        const error = new Error('postId and commentId are both required') as CustomError;
        error.status = 400;
        next(error);
    }
    try {
        await Comment.findOneAndDelete({ _id: commentId });
    } catch (err) {
        const error = new Error('comment cannot be deleted') as CustomError;
        error.status = 500;
        next(error);
    }
    const updatedPost = await Post.findOneAndUpdate(
        { _id: postId },
        { $pull: { comments: commentId } },
        { new: true }
    )
    res.status(200).send(updatedPost);
});

export { router as deleteCommentRouter };