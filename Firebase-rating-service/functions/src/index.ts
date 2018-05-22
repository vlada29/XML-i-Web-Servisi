import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';
import * as cors from 'cors';



admin.initializeApp();

const corsHandler = cors({origin: true});

export const addComment = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').push(komentar);
    });
});

export const addRating = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const ocena = request.body;
        const s = admin.database().ref('ratings').push(ocena);
    });
});

export const approveComment = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').child(komentar.cloudStorageKey).update({odobren:true});
    });
});

export const deleteComment = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').child(komentar.cloudStorageKey).remove();
        response.send('{}');
    });
});