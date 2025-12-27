import { Router, Request, Response, NextFunction } from 'express';

const router = Router();

router.post('/api/post/update/:id', async (req: Request, res: Response, next: NextFunction) => {
    const postId = req.params.id;
});

export { router as UpdatePostRouter };