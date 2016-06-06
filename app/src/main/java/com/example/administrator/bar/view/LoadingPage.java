package com.example.administrator.bar.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.bar.R;
import com.example.administrator.bar.manager.ThreadManager;
import com.example.administrator.bar.tools.UiUtils;

public abstract class LoadingPage extends FrameLayout {

	public static final int STATE_UNKOWN = 0;
	public static final int STATE_LOADING = 1;
	public static final int STATE_ERROR = 2;
	public static final int STATE_EMPTY = 3;
	public static final int STATE_SUCCESS = 4;
	public int state = STATE_UNKOWN;

	private View loadingView;
	private View errorView;
	private View emptyView;
	private View successView;

	public LoadingPage(Context context) {
		super(context);
		init();
	}

	public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LoadingPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		loadingView = createLoadingView();
		if (loadingView != null) {
			this.addView(loadingView, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		errorView = createErrorView();
		if (errorView != null) {
			this.addView(errorView, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		emptyView = createEmptyView();
		if (emptyView != null) {
			this.addView(emptyView, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		showPage();
	}

	private void showPage() {
		if (loadingView != null) {
			loadingView.setVisibility(state == STATE_UNKOWN
					|| state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
		}
		if (errorView != null) {
			errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE
					: View.INVISIBLE);
		}
		if (emptyView != null) {
			emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE
					: View.INVISIBLE);
		}
		if (state == STATE_SUCCESS) {
			if (successView == null) {
				successView = createSuccessView();
				this.addView(successView, new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}
			successView.setVisibility(View.VISIBLE);
		} else {
			if (successView != null) {
				successView.setVisibility(View.INVISIBLE);
			}
		}
	}

	private View createEmptyView() {
		View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_empty,
				null);
		return view;
	}

	private View createErrorView() {
		View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_error,
				null);
		Button page_bt = (Button) view.findViewById(R.id.page_bt);
		page_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show();
			}
		});
		return view;
	}

	private View createLoadingView() {
		View view = View.inflate(UiUtils.getContext(),
				R.layout.loadpage_loading, null);
		return view;
	}

	public enum LoadResult {
		error(2), empty(3), success(4);

		int value;

		LoadResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	public void show() {
		if (state == STATE_ERROR || state == STATE_EMPTY) {
			state = STATE_LOADING;
		}

		ThreadManager.getInstance().createLongPool().execute(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(2000);
				final LoadResult result = load();
				UiUtils.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if (result != null) {
							state = result.getValue();
							showPage();
						}
					}
				});
			}
		});
		
		showPage();

	}

	public abstract View createSuccessView();

	protected abstract LoadResult load();
}
