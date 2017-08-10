const functions = require('firebase-functions');

exports.sendNotification = functions.database.ref('/notifications/invitations/{pushId}')
    .onWrite(event => {
        const invitation = event.data.current.val();
        const senderUid = message.from;
        const receiverUid = message.to;
        const promises = [];

        // The sender and the receiver are the same person - don't send a notification
        if (senderUid == receiverUid) {
            promises.push(event.data.current.ref.remove());
            return Promise.all(promises);
        }

        const getInstanceIdPromise = admin.database().ref('/users/${receiverUid}/instanceId').once('value');
        const getReceiverUidPromise = admin.auth().getUser(receiverUid);

        return Promise.all([getInstanceIdPromise, getReceiverUidPromise]).then(results => {
            const instanceId = results[0].val();
            const receiver = results[1];
            console.log('notifying ' + receiverUid + ' about ' + message.body + ' from ' + senderUid);

            const payload = {
                notification: {
                    title: receiver.displayName,
                    body: message.body,
                    icon: receiver.photoURL
                }
            };

            admin.messaging().sendToDevice(instanceId, payload)
                .then(function (response) {
                    console.log("Successfully send message: ", response);
                })
                .catch(function (error) {
                    console.log("Error sending message: ", error);
                });
        });
    });