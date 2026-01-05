import { Router } from 'express';
import { currentUser } from '../../common/index.js';
const router = Router();
router.get('/current-user', currentUser, async (req, res, next) => {
    res.status(200).send({ currentUser: req.currentUser });
});
export { router as currentUserRouter };
//# sourceMappingURL=current-user.js.map