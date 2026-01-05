import { Router } from 'express';
const router = Router();
router.post('/signout', async (req, res, next) => {
    req.session = null;
    res.send();
});
export { router as signoutRouter };
//# sourceMappingURL=signout.js.map