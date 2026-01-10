import { Router, Request, Response, NextFunction } from 'express';
import Post from "../../models/post.js";
import { BadRequestError } from '../../common/index.js';
import { User, UserDoc } from '../../models/user.js';

const router = Router();

router.delete('/api/post/delete/:id', async (req: Request, res: Response, next: NextFunction) => {
    const postId = req.params.id;
    if (!postId) {
        return next(new BadRequestError('postId is required!'));
    }

    try {
        await Post.findOneAndDelete({ _id: postId });

    } catch (err) {
        next(new Error('post cannot be updated!'));
    }

    const user: UserDoc | null = await User.findOneAndUpdate({ _id: req.currentUser!.userId },
        { $pull: { posts: postId } }
    );
    if (!user) {
        return next(new Error());
    }

    res.status(200).send(user);
});

export { router as deletePostRouter };