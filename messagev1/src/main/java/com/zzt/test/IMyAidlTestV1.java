/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.zzt.test;
// Declare any non-default types here with import statements

public interface IMyAidlTestV1 extends android.os.IInterface {
    /**
     * Default implementation for IMyAidlTest.
     */
    public static class Default implements IMyAidlTestV1 {
        /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         */
        @Override
        public int add(int i1, int i2) throws android.os.RemoteException {
            return 0;
        }

        @Override
        public android.os.IBinder asBinder() {
            return null;
        }
    }

    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements IMyAidlTestV1 {
        private static final String DESCRIPTOR = "com.zzt.test.IMyAidlTest";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.zzt.test.IMyAidlTest interface,
         * generating a proxy if needed.
         */
        public static IMyAidlTestV1 asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IMyAidlTestV1))) {
                return ((IMyAidlTestV1) iin);
            }
            return new Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_add: {
                    data.enforceInterface(descriptor);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = this.add(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements IMyAidlTestV1 {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * Demonstrates some basic types that you can use as parameters
             * and return values in AIDL.
             */
            @Override
            public int add(int i1, int i2) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(i1);
                    _data.writeInt(i2);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
                    if (!_status && getDefaultImpl() != null) {
                        return getDefaultImpl().add(i1, i2);
                    }
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            public static IMyAidlTestV1 sDefaultImpl;
        }

        static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

        public static boolean setDefaultImpl(IMyAidlTestV1 impl) {
            // Only one user of this interface can use this function
            // at a time. This is a heuristic to detect if two different
            // users in the same process use this function.
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IMyAidlTestV1 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    public int add(int i1, int i2) throws android.os.RemoteException;
}
