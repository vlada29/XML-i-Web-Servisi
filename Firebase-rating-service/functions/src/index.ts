import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';
import * as cors from 'cors';



admin.initializeApp();

const corsHandler = cors({origin: true});

export const addComment = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').push(komentar);
        response.sendStatus(200);
    });
});

export const dodajKomentar = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').push(komentar);
        response.sendStatus(200);
    });
});

export const addRating = functions.https.onRequest((request,response) => {
    corsHandler(request,response,() => {
        const ocena = request.body;
        const s = admin.database().ref('ratings').push(ocena);
        response.sendStatus(200);
    });
});
var naObradi2 = false;
export const approveComment = functions.https.onRequest((request,response) => {
    if(!naObradi2){
    naObradi2=true;
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').child(komentar.cloudStorageKey).update({odobren:true});
        response.sendStatus(200);
        naObradi2=false;
    });
    }
});

var naObradi = false;

export const deleteComment = functions.https.onRequest((request,response) => {
    if(!naObradi){
    naObradi=true;
    corsHandler(request,response,() => {
        const komentar = request.body;
        const s = admin.database().ref('comments').child(komentar.cloudStorageKey).remove();
        response.sendStatus(200);
        naObradi=false;
    });
    }
});